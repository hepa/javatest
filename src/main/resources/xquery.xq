xquery version "3.0";

(: Az adott tanévben jelenlévő osztályok listája :)
declare function local:osztalyok($tanev as xs:string) as node()* {
  for $i in doc('rendszer')//rendszer/osztalyok/osztaly
  where $i/tanev = $tanev
  return $i
};

(: Az adott évben az iskolába járó diákok listája, név szerint növekvő sorrendben :)
declare function local:diakok($tanev as xs:string) as node()* {
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
declare function local:tanevekId-descending() as xs:token* {
  for $i in doc('rendszer')//rendszer/tanevek/tanev
  order by $i/mettol descending
  let $tanevek := $i/data(@id)
  return $tanevek
};

(: Jelenleg is az iskolába járó diákok listája, az utolsó tanév alapján :)
declare function local:aktivDiakok() as node()* {
  let $aktualisTanev := local:tanevekId-descending()[1]
  let $diakok := local:diakok($aktualisTanev)
  return $diakok  
};

(: Egy szekvencia tartalmaz-e egy csomópontot :)
declare function local:is-node-in-sequence ( $node as node()? ,$seq as node()* ) as xs:boolean {
   some $nodeInSeq in $seq satisfies $nodeInSeq is $node
};

declare function local:is-value-in-sequence ( $value as xs:string ,$seq as xs:string* ) as xs:boolean {
   some $nodeInSeq in $seq satisfies $nodeInSeq = $value
};

(: Az iskola volt diákjai, az utolsó tanévet leszamítva az összes diák :)
declare function local:inaktivDiakok() as node()* {
  let $aktualisTanev := local:tanevekId-descending()[1]
  let $diakok := local:diakok($aktualisTanev)
  for $i in doc('rendszer')//rendszer/diakok/diak  
  where not(local:is-node-in-sequence($i, $diakok))
  return $i 
};

(: Osztályfőnökök egy adott évben :)
declare function local:osztalyfonokok($tanev as xs:string) as node()* {
  let $ofok-listaja := doc('rendszer')//osztalyok/osztaly/osztalyfonok[../tanev=$tanev]
  for $i in doc('rendszer')//rendszer/tanarok/tanar
  where local:is-value-in-sequence(data($i/@id), data($ofok-listaja))
  return $i
};

let $ofok := local:osztalyfonokok('2014/2015')
return $ofok