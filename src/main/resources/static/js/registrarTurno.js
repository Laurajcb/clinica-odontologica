window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const form = document.getElementById("registrarTurnoForm");
  const fechaYHora = document.getElementById("fechaYHora");
  const selectOdontologos = document.getElementById("selectOdontologosTurno");
  const selectPacientes = document.getElementById("selectPacientesTurno");

  listarTodosOdontologos();
  listarTodosPacientes();

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const fechaFormateada = fechaYHora.value.substring(0, 10) + ' ' + fechaYHora.value.substring(11)

    const datos = {
      fechaYHora: fechaFormateada,
      odontologo: selectOdontologos.value,
      paciente: selectPacientes.value,
    };

    const configuraciones = {
      method: "POST",
      body: JSON.stringify(datos),
      headers: {
        "Content-Type": "application/json",
      },
    };

    crearTurno(configuraciones);
    form.reset();
  });

  function crearTurno(configuraciones) {
    fetch(`${url}turnos/registrar`, configuraciones)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
          alert("Turno registrado con éxito")
      })
      .catch((error) => {
        console.error(error);
        alert("Error al registrar el turno. Por favor inténtelo nuevamente")
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
    selectOdontologos.innerHTML = "";

    const odontologosOrdenados = odontologos.sort((a, b) => {
      const apellidoComparison = a.apellido.localeCompare(b.apellido);

      if (apellidoComparison === 0) {
        return a.nombre.localeCompare(b.nombre);
      }

      return apellidoComparison;
    });

    const defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.textContent = "Seleccione un odontologo";
    defaultOption.selected = true;
    selectOdontologos.appendChild(defaultOption);

    odontologosOrdenados.forEach((odontologo) => {
      const option = document.createElement("option");
      option.value = odontologo.id;
      option.textContent = odontologo.apellido + " " + odontologo.nombre;
      selectOdontologos.appendChild(option);
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
    selectPacientes.innerHTML = "";

    const pacientesOrdenados = pacientes.sort((a, b) => {
      const apellidoComparison = a.apellido.localeCompare(b.apellido);

      if (apellidoComparison === 0) {
        return a.nombre.localeCompare(b.nombre);
      }

      return apellidoComparison;
    });

    const defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.textContent = "Seleccione un paciente";
    defaultOption.selected = true;
    selectPacientes.appendChild(defaultOption);

    pacientesOrdenados.forEach((paciente) => {
      const option = document.createElement("option");
      option.value = paciente.id;
      option.textContent = paciente.apellido + " " + paciente.nombre;
      selectPacientes.appendChild(option);
    });
  }
});
