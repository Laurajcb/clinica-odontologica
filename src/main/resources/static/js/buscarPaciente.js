window.addEventListener("load", function () {
  const url = "http://localhost:8080/";
  const form = document.getElementById("buscarPacienteForm");
  const moduloInformacionPaciente = document.getElementById("moduloInformacionPaciente");
  const moduloBuscarPaciente = document.getElementById("moduloBuscarPaciente");
  let paciente;

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const pacienteId = document.getElementById("selectPacientes").value;

    buscarPaciente(pacienteId);
    moduloBuscarPaciente.style.display = "none";
    form.reset();
  });

  function buscarPaciente(pacienteId) {
    fetch(`${url}pacientes/listar/${pacienteId}`)
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        paciente = data;
        localStorage.setItem("paciente", JSON.stringify(data));
        mostrarInfoPaciente();
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function mostrarInfoPaciente() {
    limpiarModuloInformacionPaciente();
    moduloInformacionPaciente.style.display = "block";

    const nombrePaciente = document.getElementById("nombrePaciente");
    const spanNombrePaciente = document.createElement("span");
    spanNombrePaciente.textContent = paciente.apellido + ' ' + paciente.nombre;
    nombrePaciente.appendChild(spanNombrePaciente)

    const dniPaciente = document.getElementById("dniPaciente");
    const spanDniPaciente = document.createElement("span");
    spanDniPaciente.textContent = paciente.dni;
    dniPaciente.appendChild(spanDniPaciente);

    const fechaIngresoPaciente = document.getElementById("fechaIngresoPaciente");
    const spanFechaIngresoPaciente = document.createElement("span");
    spanFechaIngresoPaciente.textContent = paciente.fechaIngreso;
    fechaIngresoPaciente.appendChild(spanFechaIngresoPaciente);

    const domicilioPaciente = document.getElementById("domicilioPaciente");
    const spandomicilioPaciente = document.createElement("span");
    spandomicilioPaciente.textContent = paciente.domicilio.calle + ' ' + paciente.domicilio.numero + ' - ' + paciente.domicilio.localidad + ', ' + paciente.domicilio.provincia;
    domicilioPaciente.appendChild(spandomicilioPaciente);
  }

  function limpiarModuloInformacionPaciente() {
    removeAllChildSpans(document.getElementById("nombrePaciente"));
    removeAllChildSpans(document.getElementById("dniPaciente"));
    removeAllChildSpans(document.getElementById("fechaIngresoPaciente"));
    removeAllChildSpans(document.getElementById("domicilioPaciente"));
  }

  function removeAllChildSpans(element) {
    const spans = element.querySelectorAll("span");
    spans.forEach(span => span.remove());
  }
});
