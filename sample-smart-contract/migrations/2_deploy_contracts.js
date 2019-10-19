var SampleContract = artifacts.require("./Sample.sol");

module.exports = function(deployer) {
  deployer.deploy(SampleContract);
};