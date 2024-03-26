import axios from "axios";

export default {
  findAll() {
    return axios.get('private/api/v1/prodotto/')
      .then(response => {
        return response.data;
      })
  },
  findAllAttivi() {
    return axios.get('private/api/v1/prodotto/attivi')
      .then(response => {
        return response.data;
      })
  },
  findProdotto(idProdotto) {
    return axios.get('private/api/v1/prodotto/'+idProdotto)
      .then(response => {
        return response.data;
      })
  },

  addProdotto(param) {
    return axios.post('private/api/v1/prodotto/aggiungi', param)
      .then(response => {
        return response.data;
      })
  },
  addProdottoCustom(param) {
    return axios.post('private/api/v1/prodotto/custom/aggiungi', param)
      .then(response => {
        return response.data;
      })
  },
  editProdotto(param) {
    return axios.post('private/api/v1/prodotto/modifica', param)
      .then(response => {
        return response.data;
      })
  },
  deleteProdotto(idProdotto) {
    return axios.get('private/api/v1/prodotto/elimina/'+idProdotto)
      .then(response => {
        return response.data;
      })
  },

}
