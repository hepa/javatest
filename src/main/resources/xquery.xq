xquery version "3.0";

(: Az adott tanévben jelenlévő osztályok listája :)
declare function local:osztalyok($tanev as xs:string) as node()* {
  for $i in doc('rendszer')//rendszer/osztalyok/osztaly
  where $i/tanev = $tanev
  return $i
};

declare function local:aktivOsztalyok() as node()* {
  let $aktualisTanev := local:tanevekId-descending()[1]
  let $osztalyok := local:osztalyok($aktualisTanev)
  return $osztalyok  
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

(: Az iskola volt diákjai, avagy az utolsó tanévet leszamítva az összes diák :)
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

(: Eddig még sosem voltak osztályfőnökök :)
declare function local:sose-osztalyfonokok($tanev as xs:string) as node()* {
  let $ofok-listaja := doc('rendszer')//osztalyok/osztaly/osztalyfonok
  for $i in doc('rendszer')//rendszer/tanarok/tanar
  where not(local:is-value-in-sequence(data($i/@id), data($ofok-listaja)))
  return $i
};

(: Az iskola 3 eddigi legjobb diakja átlagot tekintve :)
declare function local:top-3-diak() as node()* {
  let $diakok := db:open('rendszer')//rendszer/diakok/diak  
  let $diak-atlag := for $i in $diakok
                      let $atlag := $i/jegyei/jegy/erdemjegy  
                      return <diak>{$i/nev}<atlag>{avg(data($atlag))}</atlag></diak>
  
  let $diak-atlag-desc := for $i in $diak-atlag order by $i/atlag descending return $i
  
  for $i in subsequence($diak-atlag-desc, 1, 3)   
  return $i
};

declare function local:top-3-diak($tanev as xs:string) as node()* {
  let $diakok := local:diakok($tanev)
  let $diak-atlag := for $i in $diakok
                      let $atlag := $i/jegyei/jegy/erdemjegy  
                      return <diak>{$i/nev}<atlag>{avg(data($atlag))}</atlag></diak>
  
  let $diak-atlag-desc := for $i in $diak-atlag order by $i/atlag descending return $i
  
  for $i in subsequence($diak-atlag-desc, 1, 3)   
  return $i  
};

declare function local:max-id-szunet($tanev as xs:string) as xs:double {
  let $id := db:open('rendszer')/rendszer/tanevek/tanev[@id=$tanev]/szunetek/szunet/@id
  let $result := if (fn:empty(fn:max($id))) then 0 else fn:max($id)
  return  $result
};

declare function local:max-id-diak() as xs:double {
  let $id := db:open('rendszer')/rendszer/diakok/diak/@id
  let $result := if (fn:empty(fn:max($id))) then 0 else fn:max($id)
  return  $result
};

declare function local:max-id-tanar() as xs:double {
  let $id := db:open('rendszer')/rendszer/tanarok/tanar/@id
  let $result := if (fn:empty(fn:max($id))) then 0 else fn:max($id)
  return  $result
};

declare function local:max-id-jegy($diak-id as xs:integer) as xs:double {
  let $id := db:open('rendszer')/rendszer/diakok/diak[@id=$diak-id]/jegyei/jegy/@id
  let $result := if (fn:empty(fn:max($id))) then 0 else fn:max($id)
  return  $result
};

declare function local:max-id-fogadoora($tanar-id as xs:integer) as xs:double {
  let $id := db:open('rendszer')/rendszer/tanarok/tanar[@id=$tanar-id]/fogadoorak/fogadoora/@id
  let $result := if (fn:empty(fn:max($id))) then 0 else fn:max($id)
  return  $result
};

declare function local:max-id-ora($osztaly-id as xs:string) as xs:double {
  let $id := db:open('rendszer')/rendszer/osztalyok/osztaly[@id=$osztaly-id]/orarend/ora/@id
  let $result := if (fn:empty(fn:max($id))) then 0 else fn:max($id)
  return  $result
};

let $ofok := local:max-id-ora('11/C-14/15') 
return $ofok