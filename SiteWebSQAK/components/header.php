<?php
$directoryURI=$_SERVER['REQUEST_URI'];
$path=parse_url($directoryURI,PHP_URL_PATH);
$components = explode('/',trim($path, '/'));
$page=end($components);
?>
<header>
  
<nav class="header">
   
      <a href="?action=voirEvents"><img src=".\img\logo.svg" alt=""></a>
    
      <ul class="nav-bar">
          <li><a href="?action=voirEvents" id="accueil"class="<?php if($page === "page-principale.php"){echo "nav-txt active";}else{echo "nav-txt";}?>">Accueil</a></li>
          <li><a href="?action=profilOrganisateur" id="profil" class="<?php if($page === "profil-organisateur.php"){echo "nav-txt active";}else{echo "nav-txt";}?>">Profil</a></li>
          <li><a href="?action=settings" id="settings" class="<?php if($page === "settings.php"){echo "nav-txt active";}else{echo "nav-txt";}?>">Paramètres</a></li>
      </ul>

</nav> 
</header>