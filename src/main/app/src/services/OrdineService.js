import axios from "axios";

export default {
  findAll() {
    return axios.get('private/api/v1/ordine/')
      .then(response => {
        return response.data;
      })
  },
  findByTableCustom(idTavolo) {
    return axios.get('private/api/v1/ordine/table/custom/'+idTavolo)
      .then(response => {
        return response.data;
      })
  },
  findByTableCustomTipologia(idTavolo,tipologia) {
    return axios.get('private/api/v1/ordine/table/custom/'+idTavolo+'/'+tipologia)
      .then(response => {
        return response.data;
      })
  },
  deleteOrdine(idOrdine) {
    return axios.get('private/api/v1/ordine/elimina/'+idOrdine)
      .then(response => {
        return response.data;
      })
  },
  addOrdine(param) {
    return axios.post('private/api/v1/ordine/aggiungi', param)
      .then(response => {
        return response.data;
      })
  },
  editOrdine(param) {
    return axios.post('private/api/v1/ordine/modifica', param)
      .then(response => {
        return response.data;
      })
  },
  findOrdine(idOrdine) {
    return axios.get('private/api/v1/ordine/'+idordine)
      .then(response => {
        return response.data;
      })
  },
  getPortate() {
    return axios.get('private/api/v1/ordine/portate')
      .then(response => {
        return response.data;
      })
  },
  getPortata(idPortata) {
    return axios.get('private/api/v1/ordine/portata/'+idPortata)
      .then(response => {
        return response.data;
      })
  },

}
