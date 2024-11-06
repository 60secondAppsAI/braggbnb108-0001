import http from "../http-common"; 

class AvailabilityService {
  getAllAvailabilitys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/availability/availabilitys`, searchDTO);
  }

  get(availabilityId) {
    return this.getRequest(`/availability/${availabilityId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/availability?field=${matchData}`, null);
  }

  addAvailability(data) {
    return http.post("/availability/addAvailability", data);
  }

  update(data) {
  	return http.post("/availability/updateAvailability", data);
  }
  
  uploadImage(data,availabilityId) {
  	return http.postForm("/availability/uploadImage/"+availabilityId, data);
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

export default new AvailabilityService();
