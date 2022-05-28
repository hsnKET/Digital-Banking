export interface AccountDetails {
  accountId:            string;
  balance:              number;
  currentPage:          number;
  totalPages:           number;
  pageSize:             number;
  accountOperationDTOS: accountOperationDTOS[];
}

export interface accountOperationDTOS {
  id:            number;
  operationDate: Date;
  amount:        number;
  type:          string;
  description:   string;
}
