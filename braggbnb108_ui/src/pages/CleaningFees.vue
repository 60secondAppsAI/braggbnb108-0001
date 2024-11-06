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
            <cleaningFee-table
            v-if="cleaningFees && cleaningFees.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:cleaningFees="cleaningFees"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-cleaning-fees="getAllCleaningFees"
             >

            </cleaningFee-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CleaningFeeTable from "@/components/CleaningFeeTable";
import CleaningFeeService from "../services/CleaningFeeService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CleaningFeeTable,
  },
  data() {
    return {
      cleaningFees: [],
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
    async getAllCleaningFees(sortBy='cleaningFeeId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CleaningFeeService.getAllCleaningFees(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.cleaningFees.length) {
					this.cleaningFees = response.data.cleaningFees;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching cleaningFees:", error);
        }
        
      } catch (error) {
        console.error("Error fetching cleaningFee details:", error);
      }
    },
  },
  mounted() {
    this.getAllCleaningFees();
  },
  created() {
    this.$root.$on('searchQueryForCleaningFeesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCleaningFees();
    })
  }
};
</script>
<style></style>
