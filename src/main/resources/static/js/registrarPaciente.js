window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const form = document.getElementById("registrarPacienteForm");

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const dni = document.getElementById("dni").value;
    const calle = document.getElementById("calle").value;
    const numero = document.getElementById("numero").value;
    const localidad = document.getElementById("localidad").value;
    const provincia = document.getElementById("provincia").value;

    const datos = {
      nombre: capitalizeFirstLetter(nombre),
      apellido: capitalizeFirstLetter(apellido),
      dni: dni,
      domicilio: {
        calle: capitalizeFirstLetter(calle),
        numero: numero,
        localidad: capitalizeFirstLetter(localidad),
        provincia: capitalizeFirstLetter(provincia),
      },
    };

    const configuraciones = {
      method: "POST",
      body: JSON.stringify(datos),
      headers: {
        "Content-Type": "application/json",
      },
    };

    crearPaciente(configuraciones);
    form.reset();
  });

  function crearPaciente(configuraciones) {
    fetch(`${url}pacientes/registrar`, configuraciones)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
          alert("Paciente registrado con éxito")
      })
      .catch((error) => {
        console.error(error);
        alert("Error al registrar el paciente. Por favor inténtelo nuevamente")
      });
  }

  function capitalizeFirstLetter (string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
  }
});
