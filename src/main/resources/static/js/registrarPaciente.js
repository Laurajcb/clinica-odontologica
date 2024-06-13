window.addEventListener("load", function () {
    const form = document.getElementById("PacientesForm")
    const url = "http://localhost:8080/"

    form.addEventListener("submit", function (event) {
        event.preventDefault()

        const nombre = document.getElementById("nombre").value
        const apellido = document.getElementById("apellido").value
        const dni = document.getElementById("dni").value
        const calle = document.getElementById("calle").value
        const numero = document.getElementById("numero").value
        const localidad = document.getElementById("localidad").value
        const provincia = document.getElementById("provincia").value

        const datos = {
            nombre: nombre,
            apellido: apellido,
            dni: dni,
            calle: calle,
            numero: numero,
            localidad: localidad,
            provincia: provincia,
        }

        const configuraciones = {
            method: "POST",
            body: JSON.stringify(datos),
            headers: {
                "Content-Type": "application/json",
            },
            mode: "no-cors",
        }

        crearPaciente(configuraciones)
        form.reset()
    })

    function crearPaciente(configuraciones){
        fetch(`${url}pacientes/registrar/`, configuraciones)
        .then ( response => {
            console.log(response)
        })
        .then(datos => {
            console.log("Respuesta servidor: ", datos)
        })
        .catch(e => {
            console.error(e)
        })
    }

})