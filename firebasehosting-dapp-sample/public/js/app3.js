App = {
  contracts: {},

  load: async () => {
    await App.loadWeb3()
    await App.loadAccount()
    await App.loadContract()
    await App.render()
  },

  // https://medium.com/metamask/https-medium-com-metamask-breaking-change-injecting-web3-7722797916a8
  loadWeb3: async () => {
    if (typeof web3 !== 'undefined') {
      App.web3Provider = web3.currentProvider
      web3 = new Web3(web3.currentProvider)
    } else {
      window.alert("Please connect to Ethereum Web3 Provider.")
    }
    // Modern dapp browsers...
    if (window.ethereum) {
      window.web3 = new Web3(ethereum)
      try {
        // Request account access if needed
        await ethereum.enable()
        // Acccounts now exposed
        web3.eth.sendTransaction({/* ... */})
      } catch (error) {
        // User denied account access...
      }
    }
    // Legacy dapp browsers...
    else if (window.web3) {
      App.web3Provider = web3.currentProvider
      window.web3 = new Web3(web3.currentProvider)
      // Acccounts always exposed
      web3.eth.sendTransaction({/* ... */})
    }
    // Non-dapp browsers...
    else {
      console.log('Non-Ethereum browser detected.')
    }
  },

  loadAccount: async () => {
    // Set the current blockchain account
    App.account = web3.eth.accounts[0]
  },

  loadContract: async () => {
    // Create a JavaScript version of the smart contract
    const sampleContract = await $.getJSON('./Sample.json')
    App.contracts.Sample = TruffleContract(sampleContract)
    App.contracts.Sample.setProvider(App.web3Provider)

    // Hydrate the smart contract with values from the blockchain
    App.sampleContract = await App.contracts.Sample.deployed()
  },

  render: async () => {

    await App.renderAgreementsData()

  },

  renderAgreementsData: async () => {
    // Load the total agreement count from the blockchain
    const agreementCount = await App.sampleContract.agreementsCounter()
      $("#loader").html("Current agreements count in the blockchain DB: "+agreementCount);
    
  },

  createAgreement: async () => {
    App.setLoading(true)
    const content = $('#newAgreement').val()
    await App.sampleContract.createNewAgreement(content)
    window.location.reload()
  },

  setLoading: (boolean) => {
    App.loading = boolean
    
  }
}

$(() => {
  $(window).load(() => {
    App.load()
  })
});