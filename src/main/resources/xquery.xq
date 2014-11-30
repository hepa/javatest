(: Az adott tanévben jelenlévő osztályok listája :)
let $tanev := "2012/2013"
for $i in //rendszer/osztalyok/osztaly
where $i/tanev = $tanev
return $i

(: Az adott évben az iskolába járó diákok listája, név szerint növekvő sorrendben :)
let $tanev := "2012/2013"
for $i in //rendszer/osztalyok/osztaly
where $i/tanev = $tanev
for $j in $i/diakok 
let $v := $j/data(diak)
for $k in //rendszer/diakok/diak
where $k[@id=$v]
order by $k/nev ascending
return $k