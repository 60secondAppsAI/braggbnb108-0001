import http from "../http-common"; 

class HostReviewService {
  getAllHostReviews(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/hostReview/hostReviews`, searchDTO);
  }

  get(hostReviewId) {
    return this.getRequest(`/hostReview/${hostReviewId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/hostReview?field=${matchData}`, null);
  }

  addHostReview(data) {
    return http.post("/hostReview/addHostReview", data);
  }

  update(data) {
  	return http.post("/hostReview/updateHostReview", data);
  }
  
  uploadImage(data,hostReviewId) {
  	return http.postForm("/hostReview/uploadImage/"+hostReviewId, data);
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

export default new HostReviewService();
