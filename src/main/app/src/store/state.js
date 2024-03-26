const state = {
  isAuthenticated: localStorage.getItem('auth'),
  user: null,
  error: null,
  loading: false,
  isLoading: false
};

export default state;
