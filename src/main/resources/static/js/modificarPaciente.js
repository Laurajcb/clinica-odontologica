window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const btnModificarPaciente = document.getElementById("modificarPaciente");
  const btnCancelarModificarPaciente = document.getElementById("cancelarModificarPaciente");
  const moduloInformacionPaciente = document.getElementById("moduloInformacionPaciente");
  const moduloModificarPaciente = document.getElementById("moduloModificarPaciente");
  const form = document.getElementById("modificarPacienteForm");
  let pacienteId;
  
  const nombre = document.getElementById("modificarNombre");
  const apellido = document.getElementById("modificarApellido");
  const dni = document.getElementById("modificarDni");
  const calle = document.getElementById("modificarCalle");
  const numero = document.getElementById("modificarNumero");
  const localidad = document.getElementById("modificarLocalidad");
  const provincia = document.getElementById("modificarProvincia");

  btnModificarPaciente.addEventListener("click", function () {
    moduloInformacionPaciente.style.display = "none";
    moduloModificarPaciente.style.display = "block";

    const paciente = JSON.parse(localStorage.getItem("paciente"));
    pacienteId = paciente.id;

    nombre.value = paciente.nombre;
    apellido.value = paciente.apellido;
    dni.value = paciente.dni;
    calle.value = paciente.domicilio.calle;
    numero.value = paciente.domicilio.numero;
    localidad.value = paciente.domicilio.localidad;
    provincia.value = paciente.domicilio.provincia;
  });

  btnCancelarModificarPaciente.addEventListener("click", function (event) {
    event.preventDefault();

    moduloInformacionPaciente.style.display = "block";
    moduloModificarPaciente.style.display = "none";
  });

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const datos = {
      nombre: capitalizeFirstLetter(nombre.value),
      apellido: capitalizeFirstLetter(apellido.value),
      dni: dni.value,
      domicilio: {
        calle: capitalizeFirstLetter(calle.value),
        numero: numero.value,
        localidad: capitalizeFirstLetter(localidad.value),
        provincia: capitalizeFirstLetter(provincia.value),
      },
    };

    const configuraciones = {
      method: "PUT",
      body: JSON.stringify(datos),
      headers: {
        "Content-Type": "application/json",
      },
    };

    modificarPaciente(pacienteId, configuraciones);
  });

  function modificarPaciente(pacienteId, configuraciones) {
    fetch(`${url}pacientes/modificar/${pacienteId}`, configuraciones)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
          alert("Paciente modificado con éxito")
      })
      .catch((error) => {
        console.error(error);
        alert("Error al modificar el paciente. Por favor inténtelo nuevamente")
      });
  }

  function capitalizeFirstLetter (string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
  }
});
