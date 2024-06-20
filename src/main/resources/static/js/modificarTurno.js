window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const btnModificarTurno = document.getElementById("modificarTurno");
  const btnCancelarModificarTurno = document.getElementById(
    "cancelarModificarTurno"
  );
  const moduloInformacionTurno = document.getElementById(
    "moduloInformacionTurno"
  );
  const moduloModificarTurno = document.getElementById("moduloModificarTurno");
  const form = document.getElementById("modificarTurnoForm");
  let turnoId;

  const fechaYHora = document.getElementById("modificarFechaYHora");
  const modificarSelectOdontologos = document.getElementById("modificarSelectOdontologos");
  const modificarSelectPacientes = document.getElementById("modificarSelectPacientes");

  const turno = JSON.parse(localStorage.getItem("turno"));

  btnModificarTurno.addEventListener("click", function () {
    moduloInformacionTurno.style.display = "none";
    moduloModificarTurno.style.display = "block";
    listarTodosOdontologos();
    listarTodosPacientes();
    turnoId = turno.id;

    fechaYHora.value = turno.fechaYHora;
    modificarSelectOdontologos.value = turno.odontologo.id;
    modificarSelectPacientes.value = turno.paciente.id;
  });

  btnCancelarModificarTurno.addEventListener("click", function (event) {
    event.preventDefault();

    moduloInformacionTurno.style.display = "block";
    moduloModificarTurno.style.display = "none";
  });

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const fechaFormateada = fechaYHora.value.substring(0, 10) + ' ' + fechaYHora.value.substring(11, 16)

    const datos = {
      fechaYHora: fechaFormateada,
      odontologo: modificarSelectOdontologos.value,
      paciente: modificarSelectPacientes.value,
    };

    const configuraciones = {
      method: "PUT",
      body: JSON.stringify(datos),
      headers: {
        "Content-Type": "application/json",
      },
    };

    modificarTurno(turnoId, configuraciones);
  });

  function modificarTurno(turnoId, configuraciones) {
    fetch(`${url}turnos/modificar/${turnoId}`, configuraciones)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        alert("Turno modificado con éxito");
      })
      .catch((error) => {
        console.error(error);
        alert("Error al modificar el turno. Por favor inténtelo nuevamente");
      });
  }

  function listarTodosOdontologos() {
    fetch(`${url}odontologos/listar/todos`)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        odontologos = Object.values(data);
        actualizarSelectOdontologos();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function actualizarSelectOdontologos() {
    modificarSelectOdontologos.innerHTML = "";

    const odontologosOrdenados = odontologos.sort((a, b) => {
      const apellidoComparison = a.apellido.localeCompare(b.apellido);

      if (apellidoComparison === 0) {
        return a.nombre.localeCompare(b.nombre);
      }

      return apellidoComparison;
    });

    odontologosOrdenados.forEach((odontologo) => {
      const option = document.createElement("option");
      option.value = odontologo.id;
      option.textContent = odontologo.apellido + " " + odontologo.nombre;
      modificarSelectOdontologos.appendChild(option);
    });
  }

  function listarTodosPacientes() {
    fetch(`${url}pacientes/listar/todos`)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        pacientes = Object.values(data);
        actualizarSelectPacientes();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function actualizarSelectPacientes() {
    modificarSelectPacientes.innerHTML = "";

    const pacientesOrdenados = pacientes.sort((a, b) => {
      const apellidoComparison = a.apellido.localeCompare(b.apellido);

      if (apellidoComparison === 0) {
        return a.nombre.localeCompare(b.nombre);
      }

      return apellidoComparison;
    });

    pacientesOrdenados.forEach((paciente) => {
      const option = document.createElement("option");
      option.value = paciente.id;
      option.textContent = paciente.apellido + " " + paciente.nombre;
      modificarSelectPacientes.appendChild(option);
    });
  }
});
