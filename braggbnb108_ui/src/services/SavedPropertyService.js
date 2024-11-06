import http from "../http-common"; 

class SavedPropertyService {
  getAllSavedPropertys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/savedProperty/savedPropertys`, searchDTO);
  }

  get(savedPropertyId) {
    return this.getRequest(`/savedProperty/${savedPropertyId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/savedProperty?field=${matchData}`, null);
  }

  addSavedProperty(data) {
    return http.post("/savedProperty/addSavedProperty", data);
  }

  update(data) {
  	return http.post("/savedProperty/updateSavedProperty", data);
  }
  
  uploadImage(data,savedPropertyId) {
  	return http.postForm("/savedProperty/uploadImage/"+savedPropertyId, data);
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

export default new SavedPropertyService();
