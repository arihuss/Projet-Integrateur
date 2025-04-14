package com.sarah.applicationsqak.vues;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sarah.applicationsqak.R;
import com.sarah.applicationsqak.viewModel.EvenementViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {
    private EventFilterView eventView;
    private EvenementViewModel viewModel;
    private EventsAdapter eventAdapter;
    private ListView lvEvents;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Liaison de la vue personnalisée 'EventFilterView'
        eventView = view.findViewById(R.id.eventFilterView);

        // Pour l'affichage des événements
        lvEvents = view.findViewById(R.id.lvEventsPrincipale);
        eventAdapter = new EventsAdapter(requireContext(), R.layout.principale_events_list_item, new ArrayList<>());
        lvEvents.setAdapter(eventAdapter);

        viewModel = new ViewModelProvider(this).get(EvenementViewModel.class);

        // Obersve les messages d'erreur
        viewModel.getMessage().observe(getViewLifecycleOwner(), message -> {
            if(message != null && !message.isEmpty()) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        // Observe les changements de LiveData et appel fonction pour que l'adaptateur affiche les nouveaux événements filtrés
        viewModel.getEvenements().observe(getViewLifecycleOwner(), evenements -> {
                    eventAdapter.clear();
                    eventAdapter.addAll(evenements);
                    eventAdapter.notifyDataSetChanged();
        });

        // Quand on filtre change, on appelle le viewModel
        eventView.setOnFilterChangeListener((lieu, etat, role, date, recherche) -> {
            viewModel.filtrerEvenements(lieu, etat, role, date, recherche);
        });




    }


}