export const ADDRESS_ID = "addressId";
export const ADDRESS_USER_ID = "userId";
export const ADDRESS_LINE1 = "address1";
export const ADDRESS_LINE2 = "address2";
export const ADDRESS_STREET = "street";
export const ADDRESS_CITY = "city";
export const ADDRESS_STATE = "state";
export const ADDRESS_COUNTRY = "country";
export const ADDRESS_PINCODE = "pincode";
export const ADDRESS_TYPE = "addressType";

let address = {
  addressId: 0,
  userId: 0,
  address1: "",
  address2: "",
  street: "",
  city: "",
  state: "",
  country: "",
  pincode: "",
  addressType: "",
};

class AddressRequest {
  constructor(address) {
    this.address = address;
  }

  getAddress() {
    return this.address;
  }
}

export default new AddressRequest(address);
