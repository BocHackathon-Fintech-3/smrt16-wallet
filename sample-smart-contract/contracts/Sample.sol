pragma solidity ^0.5.0;

contract Sample {
	uint public agreementsCounter = 0;
    struct Agreement {
	    uint id;
	    string content;
	    bool completed;
	}

	mapping(uint => Agreement) public agreements;

	constructor() public {
    }

    event AgreementCreated(
	    uint id,
	    string content,
	    bool completed
	);

	function createNewAgreement(string memory _content) public {
	    agreementsCounter ++;
	    agreements[agreementsCounter] = Agreement(agreementsCounter, _content, false);
	    emit AgreementCreated(agreementsCounter, _content, false);
	}
}