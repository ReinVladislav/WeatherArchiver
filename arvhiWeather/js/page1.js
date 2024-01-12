const url = 'http://localhost:8080/showPage/byCoordinates/'
const urlForGet = 'http://localhost:8080/showPage/getRequest'
const table = document.getElementById('tableFill')
const searchBtn = document.getElementById('searchBtn')
const coordinates = document.getElementById('coordinates')


searchBtn.onclick = async function(){
    render()
}


function fillTable(data){
    table.innerHTML=''
    for(wetherInfo of data){
        fillStrTable(wetherInfo)
    }
}

function fillStrTable(wetherInfo){

    table.insertAdjacentHTML('beforeend',`
    <tr>
        <td >${wetherInfo.temperature}</td>
        <td >${wetherInfo.humidity}</td>
        <td >${wetherInfo.windSpeed}</td>
        <td >${wetherInfo.timeSaveStr}</td>
    </tr>`
    )
}

function generateList(requests){
    coordinates.innerHTML=''
    for( i of requests){
        coordinates.insertAdjacentHTML('beforeend',
        `<option value="${i.coordinates}">${i.name ==='' ? i.coordinates : i.name +'('+ i.coordinates +')'}</option>`)
    }
}


async function render() {
    const newUrl = url+coordinates.value 
    try {
        const resp2 = await fetch(urlForGet, {method: 'GET'})
        const requests = await resp2.json()
        generateList(requests)
        const resp1 = await fetch(newUrl, {method: 'GET'})
        const data = await resp1.json()
        fillTable(data)
        if(data.length === 0){
            table.innerHTML='Информации по этим координатам ещё нет'
        }
    } catch (error) {
        table.innerHTML='Введите координаты'
    }
}

render()

