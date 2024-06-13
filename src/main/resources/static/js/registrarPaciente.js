window.addEventListener("load", function () {
    const form = document.getElementById("PacientesForm")
    const url = "http://localhost:8080/"

    form.addEventListener("submit", function (event) {
        event.preventDefault()

        const nombre = document.getElementById("nombre").value
        const apellido = document.getElementById("apellido").value
        const dni = document.getElementById("dni").value
        const fechaIngreso = '2024-06-13'
        const calle = document.getElementById("calle").value
        const numero = document.getElementById("numero").value
        const localidad = document.getElementById("localidad").value
        const provincia = document.getElementById("provincia").value

//        const  fechaValue = fechaInput.value
//        const fechaIngreso = new Date(fechaValue).toISOString().slice(0, 10)
//        console.log(fechaIngreso)


        const datos = {
            nombre: nombre,
            apellido: apellido,
            dni: dni,
            fechaIngreso: fechaIngreso,
            domicilio: {
                calle: calle,
                numero: numero,
                localidad: localidad,
                provincia: provincia,
            }

        }
        console.log(fechaIngreso)

        const configuraciones = {
            method: "POST",
            body: JSON.stringify(datos),
            headers: {
                "Content-Type": "application/json",
            },
        }

        crearPaciente(configuraciones)
        form.reset()
    })

    function crearPaciente(configuraciones){
        fetch(`${url}pacientes/registrar/`, configuraciones)
            .then(response => {
                if (!response.ok) {
                     throw new Error("Error en la solicitud: " + response.status);
                }
                 return response.json();
                 })
                 .then(datos => {
                    console.log("Respuesta servidor: ", datos);
                 })
                 .catch(e => {
                    console.error("Error al enviar la solicitud:", e);
                });
    }

})