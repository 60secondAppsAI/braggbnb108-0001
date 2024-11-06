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
            <availability-table
            v-if="availabilitys && availabilitys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:availabilitys="availabilitys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-availabilitys="getAllAvailabilitys"
             >

            </availability-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AvailabilityTable from "@/components/AvailabilityTable";
import AvailabilityService from "../services/AvailabilityService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AvailabilityTable,
  },
  data() {
    return {
      availabilitys: [],
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
    async getAllAvailabilitys(sortBy='availabilityId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AvailabilityService.getAllAvailabilitys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.availabilitys.length) {
					this.availabilitys = response.data.availabilitys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching availabilitys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching availability details:", error);
      }
    },
  },
  mounted() {
    this.getAllAvailabilitys();
  },
  created() {
    this.$root.$on('searchQueryForAvailabilitysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAvailabilitys();
    })
  }
};
</script>
<style></style>
