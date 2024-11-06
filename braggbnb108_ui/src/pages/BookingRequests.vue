<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <bookingRequest-table
            v-if="bookingRequests && bookingRequests.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:bookingRequests="bookingRequests"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-booking-requests="getAllBookingRequests"
             >

            </bookingRequest-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BookingRequestTable from "@/components/BookingRequestTable";
import BookingRequestService from "../services/BookingRequestService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BookingRequestTable,
  },
  data() {
    return {
      bookingRequests: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllBookingRequests(sortBy='bookingRequestId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BookingRequestService.getAllBookingRequests(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.bookingRequests.length) {
					this.bookingRequests = response.data.bookingRequests;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching bookingRequests:", error);
        }
        
      } catch (error) {
        console.error("Error fetching bookingRequest details:", error);
      }
    },
  },
  mounted() {
    this.getAllBookingRequests();
  },
  created() {
    this.$root.$on('searchQueryForBookingRequestsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBookingRequests();
    })
  }
};
</script>
<style></style>
