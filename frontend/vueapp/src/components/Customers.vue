<template>
  <div class="container">
    <h3>Customers:</h3>
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
        <tr v-for="customer in customers" v-bind:key="customer.id"> 
          <th scope="row">{{customer.id}}</th>
          <td>{{customer.name}}</td>
          <td>{{customer.countryName}}</td>
          <td>{{customer.valid}}</td>
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
      };
      }
      ,
    created: function() {
      axios
        .get('http://localhost:8080/getcountrycustomers')
        .then(res => {
          this.customers = res.data;
        })
    }
  }
</script>
<style>
  h3 {
    margin-bottom: 5%;
  }
</style>