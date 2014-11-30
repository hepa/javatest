xquery version "3.0";

declare namespace functx = "http://www.functx.com";

(: Az adott tanévben jelenlévő osztályok listája :)
declare function local:osztalyok($tanev) {
  for $i in doc('rendszer')//rendszer/osztalyok/osztaly
  where $i/tanev = $tanev
  return $i
};

(: Az adott évben az iskolába járó diákok listája, név szerint növekvő sorrendben :)
declare function local:diakok($tanev) {
  for $i in doc('rendszer')//rendszer/osztalyok/osztaly
  where $i/tanev = $tanev
    for $j in $i/diakok 
      let $v := $j/data(diak)
      for $k in doc('rendszer')//rendszer/diakok/diak
      where $k[@id=$v]
      order by $k/nev ascending
  return $k
};

(: A tanévek csökkenő sorrendben. :)
declare function local:tanevekId-descending() {
  for $i in doc('rendszer')//rendszer/tanevek/tanev
  order by $i/mettol descending
  let $tanevek := $i/data(@id)
  return $tanevek
};

(: Jelenleg is az iskolába járó diákok listája, az utolsó tanév alapján :)
declare function local:aktivDiakok() {
  let $aktualisTanev := local:tanevekId-descending()[1]
  let $diakok := local:diakok($aktualisTanev)
  return $diakok  
};

(: Egy szekvencia tartalmaz-e egy csomópontot :)
declare function functx:is-node-in-sequence
  ( $node as node()? ,
    $seq as node()* )  as xs:boolean {

   some $nodeInSeq in $seq satisfies $nodeInSeq is $node
};

(: Az iskola volt diákjai, az utolsó tanévet leszamítva az összes diák :)
declare function local:inAktivDiakok() {
  let $aktualisTanev := local:tanevekId-descending()[1]
  let $diakok := local:diakok($aktualisTanev)
  for $i in doc('rendszer')//rendszer/diakok/diak  
  where not(functx:is-node-in-sequence($i, $diakok))
  return $i 
};

let $diakok := local:aktivDiakok()
return $diakok
