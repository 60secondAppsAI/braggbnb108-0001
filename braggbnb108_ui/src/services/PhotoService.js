import http from "../http-common"; 

class PhotoService {
  getAllPhotos(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/photo/photos`, searchDTO);
  }

  get(photoId) {
    return this.getRequest(`/photo/${photoId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/photo?field=${matchData}`, null);
  }

  addPhoto(data) {
    return http.post("/photo/addPhoto", data);
  }

  update(data) {
  	return http.post("/photo/updatePhoto", data);
  }
  
  uploadImage(data,photoId) {
  	return http.postForm("/photo/uploadImage/"+photoId, data);
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

export default new PhotoService();
