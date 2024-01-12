const url = 'http://localhost:8080/requestPage'
const addBtn = document.getElementById('addBtn')
const coordinates = document.getElementById('coordinates')

class Request{
    constructor(coordinates, name){
        this.id = 0
        this.coordinates = coordinates
        this.name = name
    }
}


addBtn.onclick =  async function(){
    let s = prompt('Как вы назовёте эту метку?');
    let request = new Request(coordinates.value, s)
    console.log(JSON.stringify(request))
    try {
        const resp = await fetch(url,   {method: 'POST', 
                                        headers: {'Content-Type': 'application/json;charset=utf-8'},
                                        body: JSON.stringify(request)}) 
                                        alert('Вы успешно сохрании координуту' + ' \"'+  request.name+ '\"')
    } catch (error) {
        
    }
}