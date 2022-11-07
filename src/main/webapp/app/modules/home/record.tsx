import React, {useState} from 'react';
import {
  Button,
  Col,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  DropdownToggle,
  Input,
  InputGroup,
  InputGroupText,
  Row
} from "reactstrap";

export const Record = ({record}) => {

  const [personDropdownOpen, setPersonDropdownOpen] = useState(false);
  const [foodDropdownOpen, setFoodDropdownOpen] = useState(false);

  const [selectedPerson, setSelectedPerson] = useState("Тая");
  const [selectedFood, setSelectedFood] = useState("Royal Canin");
  const togglePerson = () => setPersonDropdownOpen((prevState) => !prevState);
  const toggleFood = () => setFoodDropdownOpen((prevState) => !prevState);

  return (
    <Row xs="12">
      <Col xs="1" style={{display: "flex", justifyContent: "center", alignItems: "center"}}>
        {record.index}
      </Col>
      <Col xs="3">
        <Dropdown isOpen={personDropdownOpen} toggle={togglePerson} direction={'down'} size="sm">
          <DropdownToggle caret style={{width: "-webkit-fill-available"}}>{selectedPerson}</DropdownToggle>
          <DropdownMenu>
            <DropdownItem header>Кто?</DropdownItem>
            <DropdownItem onClick={() => setSelectedPerson("Тая")}>Тая</DropdownItem>
            <DropdownItem onClick={() => setSelectedPerson("Юля")}>Юля</DropdownItem>
            <DropdownItem onClick={() => setSelectedPerson("Тима")}>Тима</DropdownItem>
            <DropdownItem onClick={() => setSelectedPerson("Артем")}>Артем</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </Col>
      <Col xs="3">
        <Dropdown isOpen={foodDropdownOpen} toggle={toggleFood} direction={'down'} size="sm">
          <DropdownToggle caret style={{width: "-webkit-fill-available"}}>{selectedFood}</DropdownToggle>
          <DropdownMenu>
            <DropdownItem header>Корм</DropdownItem>
            <DropdownItem onClick={() => setSelectedFood("Royal Canin")}>Royal Canin</DropdownItem>
            <DropdownItem onClick={() => setSelectedFood("Sheby")}>Sheby</DropdownItem>
            <DropdownItem onClick={() => setSelectedFood("Курочка")}>Курочка</DropdownItem>
          </DropdownMenu>
        </Dropdown>
      </Col>
      <Col xs="2">
        <InputGroup size="sm">
          <InputGroupText>
            gr
          </InputGroupText>
          <Input placeholder="Дали" type="number" value={record.weight} onChange={() => console.log}/>
        </InputGroup>
      </Col>
      <Col xs="2">
        <InputGroup size="sm">
          <InputGroupText>
            gr
          </InputGroupText>
          <Input placeholder="Съела" type="number" onChange={() => console.log}/>
        </InputGroup>
      </Col>
      <Col xs="1">
        <Button color="danger" size="sm" onClick={() => console.log("Delete")}>X</Button>
      </Col>
    </Row>
  )


}
