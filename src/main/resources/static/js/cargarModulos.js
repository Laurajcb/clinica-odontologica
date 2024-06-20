document.addEventListener("DOMContentLoaded", function () {
  const url = "http://localhost:8080/";
  let pacientes = [];
  let odontologos = [];
  let turnos = [];

  const btnRegistrarPaciente = document.getElementById("mostrarModuloRegistrarPaciente");
  const moduloRegistrarPaciente = document.getElementById("moduloRegistrarPaciente");
  const btnBuscarPaciente = document.getElementById("mostrarModuloBuscarPaciente");
  const moduloBuscarPaciente = document.getElementById("moduloBuscarPaciente");
  const moduloInformacionPaciente = document.getElementById("moduloInformacionPaciente");
  const moduloModificarPaciente = document.getElementById("moduloModificarPaciente");

  const btnRegistrarOdontologo = document.getElementById("mostrarModuloRegistrarOdontologo");
  const moduloRegistrarOdontologo = document.getElementById("moduloRegistrarOdontologo");
  const btnBuscarOdontologo = document.getElementById("mostrarModuloBuscarOdontologo");
  const moduloBuscarOdontologo = document.getElementById("moduloBuscarOdontologo");
  const moduloInformacionOdontologo = document.getElementById("moduloInformacionOdontologo");
  const moduloModificarOdontologo = document.getElementById("moduloModificarOdontologo");
  
  const btnRegistrarTurno = document.getElementById("mostrarModuloRegistrarTurno");
  const moduloRegistrarTurno = document.getElementById("moduloRegistrarTurno");
  const btnBuscarTurno = document.getElementById("mostrarModuloBuscarTurno");
  const moduloBuscarTurno = document.getElementById("moduloBuscarTurno");
  const moduloInformacionTurno = document.getElementById("moduloInformacionTurno");
  const moduloModificarTurno = document.getElementById("moduloModificarTurno");

  btnRegistrarPaciente.addEventListener("click", function () {
    ocultarModulosServicios();
    moduloRegistrarPaciente.style.display = "block";
  });

  btnRegistrarOdontologo.addEventListener("click", function () {
    ocultarModulosServicios();
    moduloRegistrarOdontologo.style.display = "block";
  });

  btnRegistrarTurno.addEventListener("click", function () {
    ocultarModulosServicios();
    moduloRegistrarTurno.style.display = "block";
  });

  btnBuscarPaciente.addEventListener("click", function () {
    listarTodosPacientes();
    ocultarModulosServicios();
    moduloBuscarPaciente.style.display = "block";
  });

  btnBuscarOdontologo.addEventListener("click", function () {
    listarTodosOdontologos();
    ocultarModulosServicios();
    moduloBuscarOdontologo.style.display = "block";
  });

  btnBuscarTurno.addEventListener("click", function () {
    listarTodosTurnos();
    ocultarModulosServicios();
    moduloBuscarTurno.style.display = "block";
  });

  function ocultarModulosServicios() {
    moduloRegistrarPaciente.style.display = "none";
    moduloBuscarPaciente.style.display = "none";
    moduloInformacionPaciente.style.display = "none";
    moduloModificarPaciente.style.display = "none";
    moduloRegistrarOdontologo.style.display = "none";
    moduloBuscarOdontologo.style.display = "none";
    moduloInformacionOdontologo.style.display = "none";
    moduloModificarOdontologo.style.display = "none";
    moduloRegistrarTurno.style.display = "none";
    moduloBuscarTurno.style.display = "none";
    moduloInformacionTurno.style.display = "none";
    moduloModificarTurno.style.display = "none";
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
    const selectPacientes = document.getElementById("selectPacientes");
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
    const selectOdontologos = document.getElementById("selectOdontologos");
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

  function listarTodosTurnos() {
    fetch(`${url}turnos/listar/todos`)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        turnos = Object.values(data);
        actualizarSelectTurnos();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function actualizarSelectTurnos() {
    const selectTurnos = document.getElementById("selectTurnos");
    selectTurnos.innerHTML = "";

    const turnosOrdenados = turnos.sort((a, b) => {
      return a.fechaYHora.localeCompare(b.fechaYHora);
    });

    const defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.textContent = "Seleccione un turno";
    defaultOption.selected = true;
    selectTurnos.appendChild(defaultOption);

    turnosOrdenados.forEach((turno) => {
      const option = document.createElement("option");
      option.value = turno.id;
      option.textContent = turno.fechaYHora.substring(0, 10) + ' ' + turno.fechaYHora.substring(11, 16);
      selectTurnos.appendChild(option);
    });
  }
});
