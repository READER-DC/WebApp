/**
 * 
 */
const request = new XMLHttpRequest();
request.open("POST", "http://localhost:8080/WebApp/resources/javaee10");
request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
request.send();
request.addEventListener('redystatechange', ()=>{
  if(request.redyState ===4 && request.status === 200) {
    console.log(request.respose);
  }
});