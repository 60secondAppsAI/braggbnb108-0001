import http from "../http-common"; 

class CancellationPolicyService {
  getAllCancellationPolicys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/cancellationPolicy/cancellationPolicys`, searchDTO);
  }

  get(cancellationPolicyId) {
    return this.getRequest(`/cancellationPolicy/${cancellationPolicyId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/cancellationPolicy?field=${matchData}`, null);
  }

  addCancellationPolicy(data) {
    return http.post("/cancellationPolicy/addCancellationPolicy", data);
  }

  update(data) {
  	return http.post("/cancellationPolicy/updateCancellationPolicy", data);
  }
  
  uploadImage(data,cancellationPolicyId) {
  	return http.postForm("/cancellationPolicy/uploadImage/"+cancellationPolicyId, data);
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

export default new CancellationPolicyService();
