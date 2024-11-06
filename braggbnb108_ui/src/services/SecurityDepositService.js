import http from "../http-common"; 

class SecurityDepositService {
  getAllSecurityDeposits(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/securityDeposit/securityDeposits`, searchDTO);
  }

  get(securityDepositId) {
    return this.getRequest(`/securityDeposit/${securityDepositId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/securityDeposit?field=${matchData}`, null);
  }

  addSecurityDeposit(data) {
    return http.post("/securityDeposit/addSecurityDeposit", data);
  }

  update(data) {
  	return http.post("/securityDeposit/updateSecurityDeposit", data);
  }
  
  uploadImage(data,securityDepositId) {
  	return http.postForm("/securityDeposit/uploadImage/"+securityDepositId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new SecurityDepositService();
