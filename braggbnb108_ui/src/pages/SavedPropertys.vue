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
            <savedProperty-table
            v-if="savedPropertys && savedPropertys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:savedPropertys="savedPropertys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-saved-propertys="getAllSavedPropertys"
             >

            </savedProperty-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SavedPropertyTable from "@/components/SavedPropertyTable";
import SavedPropertyService from "../services/SavedPropertyService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SavedPropertyTable,
  },
  data() {
    return {
      savedPropertys: [],
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
    async getAllSavedPropertys(sortBy='savedPropertyId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SavedPropertyService.getAllSavedPropertys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.savedPropertys.length) {
					this.savedPropertys = response.data.savedPropertys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching savedPropertys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching savedProperty details:", error);
      }
    },
  },
  mounted() {
    this.getAllSavedPropertys();
  },
  created() {
    this.$root.$on('searchQueryForSavedPropertysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSavedPropertys();
    })
  }
};
</script>
<style></style>
