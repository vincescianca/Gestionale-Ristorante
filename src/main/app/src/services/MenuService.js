import axios from "axios";

export default {
  findAll() {
    return axios.get('private/api/v1/menu/')
      .then(response => {
        return response.data;
      })
  },
  findMenuByDesc(desc) {
    return axios.get('private/api/v1/menu/descrizione/'+desc)
      .then(response => {
        return response.data;
      })
  },
  findAllBFormSelect() {
    return axios.get('private/api/v1/menu/b-form-select')
      .then(response => {
        return response.data;
      })
  },
  deleteMenu(idMenu) {
    return axios.get('private/api/v1/menu/elimina/'+idMenu)
      .then(response => {
        return response.data;
      })
  },
  cambiaStato(idMenu) {
    return axios.get('private/api/v1/menu/stato/'+idMenu)
      .then(response => {
        return response.data;
      })
  },
  addMenu(param) {
    return axios.post('private/api/v1/menu/aggiungi', param)
      .then(response => {
        return response.data;
      })
  },
  editMenu(param) {
    return axios.post('private/api/v1/menu/modifica', param)
      .then(response => {
        return response.data;
      })
  },
  findMenu(idMenu) {
    return axios.get('private/api/v1/menu/'+idMenu)
      .then(response => {
        return response.data;
      })
  },
  findMenuCustom(idMenu) {
    return axios.get('private/api/v1/menu/b-form-select/'+idMenu)
      .then(response => {
        return response.data;
      })
  },


}
