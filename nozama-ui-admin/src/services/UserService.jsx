import axios from "axios";
import {
  USER_BY_ID_SVC_URL,
  USER_BY_USERNAME_SVC_URL,
  AUTH_TOKEN_SVC_URL,
  USER_SIGNUP_SVC_URL,
} from "./common/AdminAPIConst";
import AdminAxios from "./common/AdminAxios";

class UserService {
  async authUser(username, password) {
    return await AdminAxios.post(AUTH_TOKEN_SVC_URL, {
      email: username,
      password: password,
    }).then((response) => {
      return response.data.token;
    });
  }

  async addUser(user) {
    return await AdminAxios.post(USER_SIGNUP_SVC_URL, user).then((response) => {
      console.log(response);
      return (response.status === 201);
    });
  }

  async findUserById(userid) {
    let token = await this.authUser("bob@test.com", "password");
    return await AdminAxios.get(USER_BY_ID_SVC_URL, {
      urlParams: {
        id: userid,
      },
      headers: {
        token: token,
        Authorization: `Bearer ${token}`,
      },
    }).then((response) => {
      console.log(response.data);
      return response.data;
    });
  }

  async findUserByUserName(email) {
    let token = await this.authUser("bob@test.com", "password");
    return this.findUserById(
      await AdminAxios.get(USER_BY_USERNAME_SVC_URL, {
        urlParams: {
          email: email,
        },
        headers: {
          token: token,
          Authorization: `Bearer ${token}`,
        },
      }).then((response) => {
        console.log(response);
        return response.data;
      })
    );
  }

  //TODO: Implement global search for user and make API call
  async findUserByIdOrUserName(search) {
    //let token = await this.authUser("bob@test.com", "password");
    return {
      id: search,
      username: search,
    };
  }
}

export default new UserService();
