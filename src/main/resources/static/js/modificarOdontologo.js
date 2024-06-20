window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const btnModificarOdontologo = document.getElementById("modificarOdontologo");
  const btnCancelarModificarOdontologo = document.getElementById("cancelarModificarOdontologo");
  const moduloInformacionOdontologo = document.getElementById("moduloInformacionOdontologo");
  const moduloModificarOdontologo = document.getElementById("moduloModificarOdontologo");
  const form = document.getElementById("modificarOdontologoForm");
  let odontologoId;
  
  const nombre = document.getElementById("modificarNombreOdontologo");
  const apellido = document.getElementById("modificarApellidoOdontologo");
  const matricula = document.getElementById("modificarMatricula");

  btnModificarOdontologo.addEventListener("click", function () {
    moduloInformacionOdontologo.style.display = "none";
    moduloModificarOdontologo.style.display = "block";

    const odontologo = JSON.parse(localStorage.getItem("odontologo"));
    odontologoId = odontologo.id;

    nombre.value = odontologo.nombre;
    apellido.value = odontologo.apellido;
    matricula.value = odontologo.matricula;
  });

  btnCancelarModificarOdontologo.addEventListener("click", function (event) {
    event.preventDefault();

    moduloInformacionOdontologo.style.display = "block";
    moduloModificarOdontologo.style.display = "none";
  });

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const datos = {
      nombre: capitalizeFirstLetter(nombre.value),
      apellido: capitalizeFirstLetter(apellido.value),
      matricula: matricula.value,
    };

    const configuraciones = {
      method: "PUT",
      body: JSON.stringify(datos),
      headers: {
        "Content-Type": "application/json",
      },
    };

    modificarOdontologo(odontologoId, configuraciones);
  });

  function modificarOdontologo(odontologoId, configuraciones) {
    fetch(`${url}odontologos/modificar/${odontologoId}`, configuraciones)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
          alert("Odontólogo modificado con éxito")
      })
      .catch((error) => {
        console.error(error);
        alert("Error al modificar el odontólogo. Por favor inténtelo nuevamente")
      });
  }

  function capitalizeFirstLetter (string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
  }
});
