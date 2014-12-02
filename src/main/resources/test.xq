module namespace t = 'http://test.org/modules/Test';

declare function t:hello() as xs:string {  
  let $hello := "Hello"
  return $hello
};