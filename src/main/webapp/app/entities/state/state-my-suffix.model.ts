import { BaseEntity } from './../../shared';

export class StateMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public stateName?: string,
        public countryId?: number,
        public districts?: BaseEntity[],
    ) {
    }
}
