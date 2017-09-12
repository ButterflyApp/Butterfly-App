import { BaseEntity } from './../../shared';

export class DistrictMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public districtName?: string,
        public stateId?: number,
        public locations?: BaseEntity[],
    ) {
    }
}
