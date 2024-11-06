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
            <hostReview-table
            v-if="hostReviews && hostReviews.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:hostReviews="hostReviews"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-host-reviews="getAllHostReviews"
             >

            </hostReview-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import HostReviewTable from "@/components/HostReviewTable";
import HostReviewService from "../services/HostReviewService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    HostReviewTable,
  },
  data() {
    return {
      hostReviews: [],
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
    async getAllHostReviews(sortBy='hostReviewId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await HostReviewService.getAllHostReviews(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.hostReviews.length) {
					this.hostReviews = response.data.hostReviews;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching hostReviews:", error);
        }
        
      } catch (error) {
        console.error("Error fetching hostReview details:", error);
      }
    },
  },
  mounted() {
    this.getAllHostReviews();
  },
  created() {
    this.$root.$on('searchQueryForHostReviewsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllHostReviews();
    })
  }
};
</script>
<style></style>
