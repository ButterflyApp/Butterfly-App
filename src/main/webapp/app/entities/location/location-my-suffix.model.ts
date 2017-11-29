import { BaseEntity } from './../../shared';

export class LocationMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public ownerId?: number,
        public locationName?: string,
        public image1ContentType?: string,
        public image1?: any,
        public image2ContentType?: string,
        public image2?: any,
        public image3ContentType?: string,
        public image3?: any,
        public image4ContentType?: string,
        public image4?: any,
        public image5ContentType?: string,
        public image5?: any,
        public districtId?: number,
    ) {
    }
}
