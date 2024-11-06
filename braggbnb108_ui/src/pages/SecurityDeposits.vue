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
            <securityDeposit-table
            v-if="securityDeposits && securityDeposits.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:securityDeposits="securityDeposits"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-security-deposits="getAllSecurityDeposits"
             >

            </securityDeposit-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SecurityDepositTable from "@/components/SecurityDepositTable";
import SecurityDepositService from "../services/SecurityDepositService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SecurityDepositTable,
  },
  data() {
    return {
      securityDeposits: [],
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
    async getAllSecurityDeposits(sortBy='securityDepositId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SecurityDepositService.getAllSecurityDeposits(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.securityDeposits.length) {
					this.securityDeposits = response.data.securityDeposits;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching securityDeposits:", error);
        }
        
      } catch (error) {
        console.error("Error fetching securityDeposit details:", error);
      }
    },
  },
  mounted() {
    this.getAllSecurityDeposits();
  },
  created() {
    this.$root.$on('searchQueryForSecurityDepositsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSecurityDeposits();
    })
  }
};
</script>
<style></style>
