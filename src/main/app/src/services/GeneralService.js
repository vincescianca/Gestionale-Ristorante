import axios from "axios";

export default {
  getStatistiche() {
    return axios.get('private/api/v1/generic/statistiche')
      .then(response => {
        return response.data;
      })
  },
  logged() {
    return axios.get('private/api/v1/generic/logged')
      .then(response => {
        return response.data;
      })
  },
}
