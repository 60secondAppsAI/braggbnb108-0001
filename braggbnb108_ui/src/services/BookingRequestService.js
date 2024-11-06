import http from "../http-common"; 

class BookingRequestService {
  getAllBookingRequests(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/bookingRequest/bookingRequests`, searchDTO);
  }

  get(bookingRequestId) {
    return this.getRequest(`/bookingRequest/${bookingRequestId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/bookingRequest?field=${matchData}`, null);
  }

  addBookingRequest(data) {
    return http.post("/bookingRequest/addBookingRequest", data);
  }

  update(data) {
  	return http.post("/bookingRequest/updateBookingRequest", data);
  }
  
  uploadImage(data,bookingRequestId) {
  	return http.postForm("/bookingRequest/uploadImage/"+bookingRequestId, data);
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

export default new BookingRequestService();
