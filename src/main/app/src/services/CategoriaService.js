import axios from "axios";

export default {
  findAll() {
    return axios.get('private/api/v1/categoria/')
      .then(response => {
        return response.data;
      })
  },
  findCategoria(idCategoria) {
    return axios.get('private/api/v1/categoria/'+idCategoria)
      .then(response => {
        return response.data;
      })
  },
  caricaListaTipoologie() {
    return axios.get('private/api/v1/categoria/tipologie')
      .then(response => {
        return response.data;
      })
  },
  findByIdMenu(idMenu) {
    return axios.get('private/api/v1/categoria/menu/'+idMenu)
      .then(response => {
        return response.data;
      })
  },
  findByIdMenuBFormSelect(idMenu) {
    return axios.get('private/api/v1/categoria/menu/b-form-select/'+idMenu)
      .then(response => {
        return response.data;
      })
  },
  deleteCategoria(idCategoria) {
    return axios.get('private/api/v1/categoria/elimina/'+idCategoria)
      .then(response => {
        return response.data;
      })
  },
  addCategoria(param) {
    return axios.post('private/api/v1/categoria/aggiungi', param)
      .then(response => {
        return response.data;
      })
  },
  editCategoria(param) {
    return axios.post('private/api/v1/categoria/modifica', param)
      .then(response => {
        return response.data;
      })
  },


}
