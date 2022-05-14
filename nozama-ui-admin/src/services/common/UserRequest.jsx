import AddressRequest from "./AddressRequest";

export const USER_ID = "userid";
export const USER_TYPE = "usertype";
export const USER_FULLNAME = "fullname";
export const USER_EMAIL = "email";
export const USER_MOBILE = "mobile";
export const USER_PASSWORD = "password";
export const USER_GENDER = "gender";
export const USER_ADDRESS = "address";

let user = {
  usertype: "",
  userid: 0,
  fullname: "",
  email: "",
  mobile: "",
  password: "",
  gender: "NOT_DISCLOSED",
  address: AddressRequest.getAddress(),
};
class UserRequest {
  constructor(user) {
    this.user = user;
  }

  getUser() {
    return this.user;
  }
}

export default new UserRequest(user);
