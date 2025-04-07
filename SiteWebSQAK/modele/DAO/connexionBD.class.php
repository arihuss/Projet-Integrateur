<?php
/* Description : classe permettant d'obtenir une connexion à une BD
 */

// ****** INCLUSIONS *******

// Le fichier configDB.interface.php contient le mot de passe, le nom d’utilisateur
// avec les constantes BD_HOTE, BD_NOM, BD_UTILISATEUR et BD_MOT_PASSE

include_once('configBD.interface.php');

// ********* Classe englobante de PDO *************
// L’implémentation de la classe englobante ConnexionDB se fera donc comme suit :
class ConnexionBD
{
    // Attribut représentant la connexion à la BD (de type PDO)
    private static ?PDO $instance = null;

    // Constructeur de ConnexionBD inutilisable de l’extérieur
    private function __construct()
    {
    }

    // Fonction statique qui gère la création de l’instance PDO et la retourne.
    // Note : self:: représente le nom de classe courante ConnexionBD  
    public static function getInstance(): PDO
    {
        // Si l’instance de PDO n’existe pas, on la crée 
        if (self::$instance === null) {
            // La classe utile est la classe PDO qui nous donne accès
            // à une connexion vers la base de données. 
            $host = ConfigBD::BD_HOTE;
            $db   = ConfigBD::BD_NOM;
            $user = ConfigBD::BD_UTILISATEUR;
            $pass = ConfigBD::BD_MOT_PASSE;
            $port = '3307';
            $charset = 'utf8';

            $dsn = "mysql:host=$host;port=$port;dbname=$db;charset=$charset";
            $options = [
                PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
                PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                PDO::ATTR_EMULATE_PREPARES   => false,
                PDO::MYSQL_ATTR_SSL_VERIFY_SERVER_CERT => false, // Set to true if you want to verify server certificate
            ];
            self::$instance = new PDO($dsn, $user, $pass, $options);

        }
        // Maintenant qu’on est certain qu’elle existe, on la retourne

        return self::$instance;
    }

    // Fonction qui libère la connexion PDO (pour le garbage collector)
    public static function close(): void
    {
        self::$instance = null;
    }
}
?>