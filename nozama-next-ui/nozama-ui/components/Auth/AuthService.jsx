import axios from "axios";
import AppConst from "../Util/app.const";

class AuthService {
  login(username, password) {
    console.log(AppConst().AUTH_SVC_URL);
    return axios
      .post(AppConst().AUTH_SVC_URL + "token", {
        email: username,
        password: password,
      })
      .then((response) => {
        if (response.data.token) {
          localStorage.setItem(
            AppConst().USER_TOKEN,
            JSON.stringify(response.data)
          );
        }
        return response.data;
      });
  } //EOF Login

  logout() {
    localStorage.removeItem(AppConst().USER_TOKEN);
  } //EOF Logout

  getCurrentUser() {
    return localStorage.getItem(AppConst().USER_TOKEN);
  }
}

export default new AuthService();
