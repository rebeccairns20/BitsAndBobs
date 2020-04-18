<?php

  echo "<p>love</p>";
  $carval = $_POST['carval'] ;
  $cardep = $_POST['cardep'] ;
  $caryear = $_POST['caryear'] ;

  $currentCarVal = $carval - ((2020 - $caryear) * $cardep);
  echo $currentCarVal;
 ?>
