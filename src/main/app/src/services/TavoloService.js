import axios from "axios";

export default {
  findAll() {
    return axios.get('private/api/v1/tavolo/')
      .then(response => {
        return response.data;
      })
  },
  findAllToday() {
    return axios.get('private/api/v1/tavolo/oggi')
      .then(response => {
        return response.data;
      })
  },
  findAllAttivi() {
    return axios.get('private/api/v1/tavolo/attivi')
      .then(response => {
        return response.data;
      })
  },
  findTavolo(idTavolo) {
    return axios.get('private/api/v1/tavolo/'+idTavolo)
      .then(response => {
        return response.data;
      })
  },
  chiudiTavolo(idTavolo) {
    return axios.get('private/api/v1/tavolo/chiudi/'+idTavolo)
      .then(response => {
        return response.data;
      })
  },
  apriTavolo(idTavolo) {
    return axios.get('private/api/v1/tavolo/apri/'+idTavolo)
      .then(response => {
        return response.data;
      })
  },
  addTavolo(param) {
    return axios.post('private/api/v1/tavolo/aggiungi', param)
      .then(response => {
        return response.data;
      })
  },
  editTavolo(param) {
    return axios.post('private/api/v1/tavolo/modifica', param)
      .then(response => {
        return response.data;
      })
  },
  stampaComanda(param) {
    return axios.get('private/api/v1/tavolo/stampa/'+param+'/comanda')
      .then(response => {
        return response.data;
      })
  },
  stampaNonFiscale(param) {
    return axios.get('private/api/v1/tavolo/stampa/'+param+'/cliente')
      .then(response => {
        return response.data;
      })
  },
  stampaParziale(idTavolo, ordini) {
    return axios.post('private/api/v1/tavolo/stampa/'+idTavolo, ordini)
      .then(response => {
        return response.data;
      })
  },

}
