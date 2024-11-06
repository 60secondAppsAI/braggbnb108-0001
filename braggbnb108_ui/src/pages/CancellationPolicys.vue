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
            <cancellationPolicy-table
            v-if="cancellationPolicys && cancellationPolicys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:cancellationPolicys="cancellationPolicys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-cancellation-policys="getAllCancellationPolicys"
             >

            </cancellationPolicy-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CancellationPolicyTable from "@/components/CancellationPolicyTable";
import CancellationPolicyService from "../services/CancellationPolicyService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CancellationPolicyTable,
  },
  data() {
    return {
      cancellationPolicys: [],
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
    async getAllCancellationPolicys(sortBy='cancellationPolicyId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CancellationPolicyService.getAllCancellationPolicys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.cancellationPolicys.length) {
					this.cancellationPolicys = response.data.cancellationPolicys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching cancellationPolicys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching cancellationPolicy details:", error);
      }
    },
  },
  mounted() {
    this.getAllCancellationPolicys();
  },
  created() {
    this.$root.$on('searchQueryForCancellationPolicysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCancellationPolicys();
    })
  }
};
</script>
<style></style>
