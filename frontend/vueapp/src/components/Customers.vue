<template>
  <div class="container">
    <h3>Customers:</h3>
    <input
        type="text"
        placeholder="Search Country..."
        v-model="searchCountry">
        <input
        type="text"
        placeholder="Search State..."
        v-model="searchState">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Name</th>
          <th scope="col">Country</th>
          <th scope="col">State</th>
          <th scope="col">Phone Number</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="customer in filteredData" v-bind:key="customer.id"> 
          <th scope="row">{{customer.id}}</th>
          <td>{{customer.name}}</td>
          <td>{{customer.countryName}}</td>
          <td>{{customer.state}}</td>
          <td>{{customer.phoneNumber}}</td>
        </tr>
      </tbody>
    </table> 
  </div> 
</template>
<script>
  import axios from 'axios';
  export default {
    name: 'Customers',
    data() {
      return {
        customers: null,
         searchCountry: "",
         searchState: ""
      };
      },
    created: function() {
      axios
        .get('http://localhost:8080/getcountrycustomers')
        .then(res => {
          this.customers = res.data;
        })
    },computed: {
        filteredData: function() {
            if(this.customers == null ) return;
            return this.customers.filter((items) => {
            console.log(this.searchCountry);
            console.log(this.searchState);
            console.log(items);
                if( (items["countryName"].toLowerCase().includes(this.searchCountry.toLowerCase())) && 
                 (items["state"].toLowerCase().startsWith(this.searchState.toLowerCase()) )) {
                return true
                }
            else {
                return false}
            })
        }
        }
  }
</script>
<style>
  h3 {
    margin-bottom: 5%;
  }
</style>