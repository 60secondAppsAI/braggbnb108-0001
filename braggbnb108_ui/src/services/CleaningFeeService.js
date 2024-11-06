import http from "../http-common"; 

class CleaningFeeService {
  getAllCleaningFees(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/cleaningFee/cleaningFees`, searchDTO);
  }

  get(cleaningFeeId) {
    return this.getRequest(`/cleaningFee/${cleaningFeeId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/cleaningFee?field=${matchData}`, null);
  }

  addCleaningFee(data) {
    return http.post("/cleaningFee/addCleaningFee", data);
  }

  update(data) {
  	return http.post("/cleaningFee/updateCleaningFee", data);
  }
  
  uploadImage(data,cleaningFeeId) {
  	return http.postForm("/cleaningFee/uploadImage/"+cleaningFeeId, data);
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

export default new CleaningFeeService();
